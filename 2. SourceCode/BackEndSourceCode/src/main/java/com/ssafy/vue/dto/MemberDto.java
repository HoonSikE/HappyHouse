package com.ssafy.vue.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Member (회원정보)", description = "ID, PW, 이름, 닉네임, 이메일, 생성일, 수정일, 전화번호, 권한을 가진   Domain Class")
public class MemberDto {
    // 멤버 ID
	@ApiModelProperty(value = "ID")
	private String id;

	// 멤버 비밀번호
	@ApiModelProperty(value = "PW")
    private String password;
  
	// 멤버 이름
	@ApiModelProperty(value = "이름")
    private String name;
  
    // 멤버 닉네임
    @ApiModelProperty(value = "닉네임")
    private String nickname;
  
    // 멤버 이메일
    @ApiModelProperty(value = "이메일")
    private String email;
  
    // 멤버 생성 날짜
    @ApiModelProperty(value = "생성일")
    private LocalDateTime cdate;
 
    // 멤버 수정 날짜
    @ApiModelProperty(value = "수정일")
    private LocalDateTime udate;
  
    // 멤버 전화번호
    @ApiModelProperty(value = "전화번호")
    private String tel;
  
    // 멤버 권한
    @ApiModelProperty(value = "권한")
    private String role;

    public MemberDto() {
        super();
    }

    public MemberDto(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public MemberDto(String id, String password, String name, String nickname, String email, LocalDateTime cdate, LocalDateTime udate,
                     String tel, String role) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.cdate = cdate;
        this.udate = udate;
        this.tel = tel;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public LocalDateTime getUdate() {
        return udate;
    }

    public void setUdate(LocalDateTime udate) {
        this.udate = udate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", nickname=" + nickname
                + ", email=" + email + ", cdate=" + cdate + ", udate=" + udate + ", tel=" + tel + "]";
    }
}
