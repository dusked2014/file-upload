package com.neo.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "neo_file")
public class NeoFile {
    @Id
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_link_url")
    private String fileLinkUrl;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * @return file_link_url
     */
    public String getFileLinkUrl() {
        return fileLinkUrl;
    }

    /**
     * @param fileLinkUrl
     */
    public void setFileLinkUrl(String fileLinkUrl) {
        this.fileLinkUrl = fileLinkUrl == null ? null : fileLinkUrl.trim();
    }

    /**
     * @return file_path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}