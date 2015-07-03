/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.core;

import org.eastbar.center.statusMachine.basis.Center;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author C.lins@aliyun.com
 * @date 2015年05月04
 * @time 上午11:04
 * @description : Singlon类
 */
public class StatusSnapshotFactory implements Externalizable {

    private final static Logger log = LoggerFactory.getLogger(StatusSnapshotFactory.class);

    private final File centerFile;
    private Center snapshot;
    private static StatusSnapshotFactory snapshotFactory;

    public StatusSnapshotFactory(File centerFile){
        this.centerFile = centerFile;
    }

    public static StatusSnapshotFactory getInstance(){
        if(snapshotFactory==null){
            throw new RuntimeException("StatusSnapshotFactory没有被初始化，请先初始化。");
        }
        return snapshotFactory;
    }

    public static void init(File rootFile){
        if(snapshotFactory==null){
            log.info("StatusSnapshotFactory初始化成功");
            snapshotFactory = new StatusSnapshotFactory(rootFile);
        }else{
            throw new RuntimeException("StatusSnapshotFactory已经初始化，请勿重复初始化");
        }
    }

    public synchronized void saveToFile(Center center) {
        this.snapshot = center;
        ObjectOutputStream out = null;
        try {
            FileOutputStream fout = new FileOutputStream(centerFile);
            out = new ObjectOutputStream(new BufferedOutputStream(fout));
            writeExternal(out);
            out.flush();
        } catch (Throwable e) {
            log.error("序列化CenterSnapShot出错 ：", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized Center readImage() {
        if (centerFile.exists() && !centerFile.isDirectory()) {
            return read();
        }
        return snapshot;
    }

    /**
     * 初始化从序列化文件读取内容
     */
    protected Center read() {
        ObjectInputStream in = null;
        try {
            if(centerFile.length()!=0){
                FileInputStream fin = new FileInputStream(centerFile);
                in = new ObjectInputStream(new BufferedInputStream(fin));
                readExternal(in);
            }
            return snapshot;
        } catch (Throwable e) {
            log.error("反序列化CenterSnapShot出错 ：", e);
            return snapshot;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(snapshot);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        snapshot = (Center) in.readObject();
        log.info("Sites状态内存区已恢复至："+snapshot.getLastUpDate());
    }
}
