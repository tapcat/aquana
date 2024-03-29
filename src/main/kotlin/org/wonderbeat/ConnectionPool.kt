package org.wonderbeat

import org.apache.commons.pool2.BasePooledObjectFactory
import org.apache.commons.pool2.ObjectPool
import org.apache.commons.pool2.PooledObject
import org.apache.commons.pool2.impl.DefaultPooledObject
import org.apache.commons.pool2.impl.GenericObjectPool
import org.apache.commons.pool2.impl.GenericObjectPoolConfig

class PartitionConnectionPool<T>(val connections: ConnectionsPool<T>,
                                 val partitionToHostLeader: Map<Int, HostPort>) {
    fun borrowConnection(partition: Int): T? = connections.hostToConnection[partitionToHostLeader[partition]!!]?.borrowObject()
    fun returnConnection(partition: Int, con: T) = connections.hostToConnection[partitionToHostLeader[partition]!!]!!.returnObject(con)
}

class ConnectionsPool<T>(hostList: Collection<HostPort>,
                         private val constructor: (hostPort: HostPort) -> T,
                         private val destructor: (T) -> Unit,
                         private val poolCfg: GenericObjectPoolConfig = ConnectionsPool.defaultPoolCfg()) {

    companion object {
        public fun defaultPoolCfg(): GenericObjectPoolConfig {
            val poolCfg = GenericObjectPoolConfig()
            poolCfg.maxIdle = 5
            poolCfg.maxTotal = 6
            poolCfg.minIdle = 2
            return poolCfg
        }
    }

    private fun internalFactory(host: HostPort) = object: BasePooledObjectFactory<T>() {
        override fun create(): T = constructor(host)
        override fun destroyObject(p: PooledObject<T>) = destructor(p.`object`)
        override fun wrap(obj: T): PooledObject<T>? = DefaultPooledObject<T>(obj)
    }

    fun close() = hostToConnection.forEach { it.value.close() }

    val hostToConnection: Map<HostPort, ObjectPool<T>> = hostList.associateBy({it},
            { a -> GenericObjectPool<T>(internalFactory(a), poolCfg)})

}
