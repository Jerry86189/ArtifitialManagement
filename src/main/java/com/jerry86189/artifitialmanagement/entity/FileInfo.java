package com.jerry86189.artifitialmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: FileInfo
 * Description: TODO
 * date: 2023/06/10 16:48
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file_info")
public class FileInfo implements Serializable {
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    @TableField("uploader_id")
    private Long uploaderId;

    @TableField("file_name")
    private String fileName;

    @TableField("upload_timestamp")
    private Timestamp uploadTimestamp;

    @TableField("file_size")
    private Long fileSize;

    @TableField("file_path")
    private String filePath;
}
