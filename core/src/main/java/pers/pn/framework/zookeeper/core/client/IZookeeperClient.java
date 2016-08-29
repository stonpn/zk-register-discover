package pers.pn.framework.zookeeper.core.client;

import org.apache.curator.framework.CuratorFramework;

import java.util.List;

/**
 * Created by pn on 16/8/29.
 */
public interface IZookeeperClient {

    /**
     * 创建节点
     * @param path
     */
    void create(String path);

    /**
     * 创建临时节点
     * @param path
     */
    void createTemp(String path);

    /**
     * 设置节点数据
     * @param path
     * @param content
     */
    void setData(String path,byte[] content);

    /**
     * 读取节点数据
     * @param path
     * @return
     */
    byte[] readByte(String path) throws Exception;

    /**
     * 删除节点数据
     * @param path
     */
    void deleteNode(String path);

    /**
     * 检查节点是否存在
     * @param path
     * @return
     */
    boolean checkExist(String path);

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
    CuratorFramework getClient();

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
