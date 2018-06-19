package com.yzx.xiaomusic.service;

/**
 * @author yzx
 * @date 2018/6/19
 * Description 服务管理类
 */
public class ServiceManager {

    private static ServiceManager serviceManager;
    private MusicService service;

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (serviceManager == null) {
            serviceManager = new ServiceManager();
        }
        return serviceManager;
    }

    public void setService(MusicService service) {
        this.service = service;
    }

    public MusicService getService() {
        return service;
    }
}
