
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User {

    @SerializedName("accessFailedCount")
    private Long mAccessFailedCount;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("age")
    private Long mAge;
    @SerializedName("area")
    private Area mArea;
    @SerializedName("areaId")
    private Long mAreaId;
    @SerializedName("authenticationSource")
    private String mAuthenticationSource;
    @SerializedName("birthDate")
    private String mBirthDate;
    @SerializedName("branchId")
    private Long mBranchId;
    @SerializedName("city")
    private City mCity;
    @SerializedName("cityId")
    private Long mCityId;
    @SerializedName("claims")
    private List<Claim> mClaims;
    @SerializedName("clientAddresses")
    private List<ClientAddress> mClientAddresses;
    @SerializedName("concurrencyStamp")
    private String mConcurrencyStamp;
    @SerializedName("country")
    private Country mCountry;
    @SerializedName("countryId")
    private Long mCountryId;
    @SerializedName("courierStatus")
    private Long mCourierStatus;
    @SerializedName("courierVehicles")
    private CourierVehicles mCourierVehicles;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("currentJob")
    private String mCurrentJob;
    @SerializedName("currentLatitude")
    private String mCurrentLatitude;
    @SerializedName("currentLongitude")
    private String mCurrentLongitude;
    @SerializedName("deleterUserId")
    private Long mDeleterUserId;
    @SerializedName("deletionTime")
    private String mDeletionTime;
    @SerializedName("emailAddress")
    private String mEmailAddress;
    @SerializedName("emailConfirmationCode")
    private String mEmailConfirmationCode;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("fullName")
    private String mFullName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isActive")
    private Boolean mIsActive;
    @SerializedName("isAlwaysOpen")
    private Boolean mIsAlwaysOpen;
    @SerializedName("isDeleted")
    private Boolean mIsDeleted;
    @SerializedName("isEmailConfirmed")
    private Boolean mIsEmailConfirmed;
    @SerializedName("isExternalLogin")
    private Boolean mIsExternalLogin;
    @SerializedName("isLockoutEnabled")
    private Boolean mIsLockoutEnabled;
    @SerializedName("isPhoneNumberConfirmed")
    private Boolean mIsPhoneNumberConfirmed;
    @SerializedName("isTwoFactorEnabled")
    private Boolean mIsTwoFactorEnabled;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("lockoutEndDateUtc")
    private String mLockoutEndDateUtc;
    @SerializedName("logins")
    private List<Login> mLogins;
    @SerializedName("name")
    private String mName;
    @SerializedName("nationalId")
    private Long mNationalId;
    @SerializedName("nationality")
    private Nationality mNationality;
    @SerializedName("nationalityId")
    private Long mNationalityId;
    @SerializedName("normalizedEmailAddress")
    private String mNormalizedEmailAddress;
    @SerializedName("normalizedUserName")
    private String mNormalizedUserName;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("passwordResetCode")
    private String mPasswordResetCode;
    @SerializedName("permissions")
    private List<Permission> mPermissions;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("qualification")
    private String mQualification;
    @SerializedName("roles")
    private List<Role> mRoles;
    @SerializedName("secondName")
    private String mSecondName;
    @SerializedName("securityStamp")
    private String mSecurityStamp;
    @SerializedName("settings")
    private List<Setting> mSettings;
    @SerializedName("surname")
    private String mSurname;
    @SerializedName("tenantId")
    private Long mTenantId;
    @SerializedName("thirdName")
    private String mThirdName;
    @SerializedName("tokens")
    private List<Token> mTokens;
    @SerializedName("userImage")
    private String mUserImage;
    @SerializedName("userName")
    private String mUserName;
    @SerializedName("usersAttachments")
    private List<UsersAttachment> mUsersAttachments;
    @SerializedName("workingHours")
    private List<WorkingHour> mWorkingHours;

    public Long getAccessFailedCount() {
        return mAccessFailedCount;
    }

    public void setAccessFailedCount(Long accessFailedCount) {
        mAccessFailedCount = accessFailedCount;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Long getAge() {
        return mAge;
    }

    public void setAge(Long age) {
        mAge = age;
    }

    public Area getArea() {
        return mArea;
    }

    public void setArea(Area area) {
        mArea = area;
    }

    public Long getAreaId() {
        return mAreaId;
    }

    public void setAreaId(Long areaId) {
        mAreaId = areaId;
    }

    public String getAuthenticationSource() {
        return mAuthenticationSource;
    }

    public void setAuthenticationSource(String authenticationSource) {
        mAuthenticationSource = authenticationSource;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public Long getBranchId() {
        return mBranchId;
    }

    public void setBranchId(Long branchId) {
        mBranchId = branchId;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public Long getCityId() {
        return mCityId;
    }

    public void setCityId(Long cityId) {
        mCityId = cityId;
    }

    public List<Claim> getClaims() {
        return mClaims;
    }

    public void setClaims(List<Claim> claims) {
        mClaims = claims;
    }

    public List<ClientAddress> getClientAddresses() {
        return mClientAddresses;
    }

    public void setClientAddresses(List<ClientAddress> clientAddresses) {
        mClientAddresses = clientAddresses;
    }

    public String getConcurrencyStamp() {
        return mConcurrencyStamp;
    }

    public void setConcurrencyStamp(String concurrencyStamp) {
        mConcurrencyStamp = concurrencyStamp;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        mCountry = country;
    }

    public Long getCountryId() {
        return mCountryId;
    }

    public void setCountryId(Long countryId) {
        mCountryId = countryId;
    }

    public Long getCourierStatus() {
        return mCourierStatus;
    }

    public void setCourierStatus(Long courierStatus) {
        mCourierStatus = courierStatus;
    }

    public CourierVehicles getCourierVehicles() {
        return mCourierVehicles;
    }

    public void setCourierVehicles(CourierVehicles courierVehicles) {
        mCourierVehicles = courierVehicles;
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

    public String getCurrentJob() {
        return mCurrentJob;
    }

    public void setCurrentJob(String currentJob) {
        mCurrentJob = currentJob;
    }

    public String getCurrentLatitude() {
        return mCurrentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        mCurrentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return mCurrentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        mCurrentLongitude = currentLongitude;
    }

    public Long getDeleterUserId() {
        return mDeleterUserId;
    }

    public void setDeleterUserId(Long deleterUserId) {
        mDeleterUserId = deleterUserId;
    }

    public String getDeletionTime() {
        return mDeletionTime;
    }

    public void setDeletionTime(String deletionTime) {
        mDeletionTime = deletionTime;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }

    public String getEmailConfirmationCode() {
        return mEmailConfirmationCode;
    }

    public void setEmailConfirmationCode(String emailConfirmationCode) {
        mEmailConfirmationCode = emailConfirmationCode;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Boolean getIsAlwaysOpen() {
        return mIsAlwaysOpen;
    }

    public void setIsAlwaysOpen(Boolean isAlwaysOpen) {
        mIsAlwaysOpen = isAlwaysOpen;
    }

    public Boolean getIsDeleted() {
        return mIsDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        mIsDeleted = isDeleted;
    }

    public Boolean getIsEmailConfirmed() {
        return mIsEmailConfirmed;
    }

    public void setIsEmailConfirmed(Boolean isEmailConfirmed) {
        mIsEmailConfirmed = isEmailConfirmed;
    }

    public Boolean getIsExternalLogin() {
        return mIsExternalLogin;
    }

    public void setIsExternalLogin(Boolean isExternalLogin) {
        mIsExternalLogin = isExternalLogin;
    }

    public Boolean getIsLockoutEnabled() {
        return mIsLockoutEnabled;
    }

    public void setIsLockoutEnabled(Boolean isLockoutEnabled) {
        mIsLockoutEnabled = isLockoutEnabled;
    }

    public Boolean getIsPhoneNumberConfirmed() {
        return mIsPhoneNumberConfirmed;
    }

    public void setIsPhoneNumberConfirmed(Boolean isPhoneNumberConfirmed) {
        mIsPhoneNumberConfirmed = isPhoneNumberConfirmed;
    }

    public Boolean getIsTwoFactorEnabled() {
        return mIsTwoFactorEnabled;
    }

    public void setIsTwoFactorEnabled(Boolean isTwoFactorEnabled) {
        mIsTwoFactorEnabled = isTwoFactorEnabled;
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

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLockoutEndDateUtc() {
        return mLockoutEndDateUtc;
    }

    public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
        mLockoutEndDateUtc = lockoutEndDateUtc;
    }

    public List<Login> getLogins() {
        return mLogins;
    }

    public void setLogins(List<Login> logins) {
        mLogins = logins;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getNationalId() {
        return mNationalId;
    }

    public void setNationalId(Long nationalId) {
        mNationalId = nationalId;
    }

    public Nationality getNationality() {
        return mNationality;
    }

    public void setNationality(Nationality nationality) {
        mNationality = nationality;
    }

    public Long getNationalityId() {
        return mNationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        mNationalityId = nationalityId;
    }

    public String getNormalizedEmailAddress() {
        return mNormalizedEmailAddress;
    }

    public void setNormalizedEmailAddress(String normalizedEmailAddress) {
        mNormalizedEmailAddress = normalizedEmailAddress;
    }

    public String getNormalizedUserName() {
        return mNormalizedUserName;
    }

    public void setNormalizedUserName(String normalizedUserName) {
        mNormalizedUserName = normalizedUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPasswordResetCode() {
        return mPasswordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        mPasswordResetCode = passwordResetCode;
    }

    public List<Permission> getPermissions() {
        return mPermissions;
    }

    public void setPermissions(List<Permission> permissions) {
        mPermissions = permissions;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getQualification() {
        return mQualification;
    }

    public void setQualification(String qualification) {
        mQualification = qualification;
    }

    public List<Role> getRoles() {
        return mRoles;
    }

    public void setRoles(List<Role> roles) {
        mRoles = roles;
    }

    public String getSecondName() {
        return mSecondName;
    }

    public void setSecondName(String secondName) {
        mSecondName = secondName;
    }

    public String getSecurityStamp() {
        return mSecurityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        mSecurityStamp = securityStamp;
    }

    public List<Setting> getSettings() {
        return mSettings;
    }

    public void setSettings(List<Setting> settings) {
        mSettings = settings;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public Long getTenantId() {
        return mTenantId;
    }

    public void setTenantId(Long tenantId) {
        mTenantId = tenantId;
    }

    public String getThirdName() {
        return mThirdName;
    }

    public void setThirdName(String thirdName) {
        mThirdName = thirdName;
    }

    public List<Token> getTokens() {
        return mTokens;
    }

    public void setTokens(List<Token> tokens) {
        mTokens = tokens;
    }

    public String getUserImage() {
        return mUserImage;
    }

    public void setUserImage(String userImage) {
        mUserImage = userImage;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public List<UsersAttachment> getUsersAttachments() {
        return mUsersAttachments;
    }

    public void setUsersAttachments(List<UsersAttachment> usersAttachments) {
        mUsersAttachments = usersAttachments;
    }

    public List<WorkingHour> getWorkingHours() {
        return mWorkingHours;
    }

    public void setWorkingHours(List<WorkingHour> workingHours) {
        mWorkingHours = workingHours;
    }

}
