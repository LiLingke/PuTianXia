package com.putianxia.bin;

import java.util.ArrayList;
import java.util.List;


public class ImageFloder {
	 /**
     * ͼƬ���ļ���·��
     */
    private String dir;

    /**
     * ��һ��ͼƬ��·��
     */
    private String firstImagePath;
    /**
     * �ļ��е�����
     */
    private String name;

    public List<ImageItem> images = new ArrayList<ImageItem>();

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        int lastIndexOf = this.dir.lastIndexOf("/");
        this.name = this.dir.substring(lastIndexOf);
    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public String getName() {
        return name;
    }

}
