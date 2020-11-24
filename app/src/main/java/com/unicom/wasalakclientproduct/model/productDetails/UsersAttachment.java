
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UsersAttachment {

    @SerializedName("attachmentTypes")
    private AttachmentTypes mAttachmentTypes;
    @SerializedName("attachmentTypesId")
    private Long mAttachmentTypesId;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("extention")
    private String mExtention;
    @SerializedName("id")
    private Long mId;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("name")
    private String mName;
    @SerializedName("path")
    private String mPath;
    @SerializedName("userId")
    private Long mUserId;

    public AttachmentTypes getAttachmentTypes() {
        return mAttachmentTypes;
    }

    public void setAttachmentTypes(AttachmentTypes attachmentTypes) {
        mAttachmentTypes = attachmentTypes;
    }

    public Long getAttachmentTypesId() {
        return mAttachmentTypesId;
    }

    public void setAttachmentTypesId(Long attachmentTypesId) {
        mAttachmentTypesId = attachmentTypesId;
    }

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public Long getCreatorUserId() {
        return mCreatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        mCreatorUserId = creatorUserId;
    }

    public String getExtention() {
        return mExtention;
    }

    public void setExtention(String extention) {
        mExtention = extention;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastModificationTime() {
        return mLastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        mLastModificationTime = lastModificationTime;
    }

    public Long getLastModifierUserId() {
        return mLastModifierUserId;
    }

    public void setLastModifierUserId(Long lastModifierUserId) {
        mLastModifierUserId = lastModifierUserId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}
