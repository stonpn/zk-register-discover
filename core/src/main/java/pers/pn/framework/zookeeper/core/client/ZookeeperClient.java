package pers.pn.framework.zookeeper.core.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NodeExistsException;

import java.util.List;

/**
 * Created by apple on 16/8/29.
 */
public class ZookeeperClient implements IZookeeperClient {

    private final CuratorFramework client;

    public ZookeeperClient(String path,String namespace,int sessionTimeout,int connectionTimeout) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder().connectString(path).namespace(namespace)
                .sessionTimeoutMs(sessionTimeout).connectionTimeoutMs(connectionTimeout)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE,1000)).build();
        try {
            curatorFramework.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        this.client = curatorFramework;
    }
    /**
     * 创建节点
     * @param path
     */
    public void create(String path) {
        try{
            client.create().creatingParentsIfNeeded().forPath(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 创建临时节点
     * @param path
     */
    public void createTemp(String path) {
        try{
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置节点数据
     * @param path
     * @param content
     */
    public void setData(String path,byte[] content) {
        try {
            client.setData().forPath(path, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取节点数据
     * @param path
     * @return
     */
    public byte[] readByte(String path) throws Exception {
        return client.getData().forPath(path);
    }

    /**
     * 删除节点数据
     * @param path
     */
    public void deleteNode(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查节点是否存在
     * @param path
     * @return
     */
    public boolean checkExist(String path) {
        boolean exist = false;
        try {
            exist = client.checkExists().forPath(path) == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 获取子节点
     * @param path
     * @return
     */
    List<String> getChildren(String path);

    /**
     * 获取zookeeper连接
     * @return
     */
    public CuratorFramework getClient() {
        return client;
    }

    /**
     * 检查当前客户端是否连接服务端
     * @return
     */
    boolean isConnected();

    /**
     * 关闭zookeeper连接
     */
    void close();
}
