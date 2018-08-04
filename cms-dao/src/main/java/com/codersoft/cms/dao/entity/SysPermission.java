package com.codersoft.cms.dao.entity;

import com.codersoft.cms.dao.dto.PageDto;

import java.io.Serializable;
import java.util.Date;

public class SysPermission extends PageDto implements Serializable {

    private Long permissionId;

    private Long parentId;

    private String perCode;

    private String perName;

    private String uri;

    private String iconName;

    private Byte perType;

    private Byte perLevel;

    private Byte status;

    private String description;

    private Byte isDelete;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode == null ? null : perCode.trim();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName == null ? null : iconName.trim();
    }

    public Byte getPerType() {
        return perType;
    }

    public void setPerType(Integer perType) {
        this.perType = perType == null ? null : (byte) perType.intValue();
    }

    public Byte getPerLevel() { return perLevel;}

    public void setPerLevel(Integer perLevel) {
        this.perLevel = perLevel == null ? null : (byte) perLevel.intValue();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == null ? null : (byte) status.intValue();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permissionId=").append(permissionId);
        sb.append(", parentId=").append(parentId);
        sb.append(", perCode=").append(perCode);
        sb.append(", perName=").append(perName);
        sb.append(", uri=").append(uri);
        sb.append(", iconName=").append(iconName);
        sb.append(", type=").append(perType);
        sb.append(", perLevel=").append(perLevel);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyBy=").append(modifyBy);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 父级资源名称
     **/
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }
}