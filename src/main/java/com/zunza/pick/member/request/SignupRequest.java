package com.zunza.pick.member.request;

import org.hibernate.validator.constraints.Length;

import com.zunza.pick.annotations.SpecificValue;
import com.zunza.pick.member.constant.MemberType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

	@Email(message = "유효한 이메일 형식이 아닙니다.")
	@NotBlank(message = "이메일을 입력해 주세요.")
	private String email;

	@SpecificValue(acceptedValues = "TRUE", message = "이메일 중복 확인을 진행해 주세요.")
	private String isEmailVerified;

	@NotBlank(message = "비밀번호를 입력해 주세요.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자~16자로 설정해 주세요.")
	private String password;

	@NotBlank(message = "이름을 입력해 주세요.")
	@Length(min = 2, max = 5, message = "이름은 2자~5자로 설정해 주세요. 5자 이상 일 경우 고객센터로 문의해 주세요.")
	private String name;

	@NotBlank(message = "닉네임을 입력해 주세요.")
	@Length(min = 2, max = 10, message = "닉네임은 2자~10자로 설정해 주세요.")
	private String nickname;

	@SpecificValue(acceptedValues = "TRUE", message = "닉네임 중복 확인을 진행해 주세요.")
	private String isNicknameVerified;


	@NotBlank(message = "주소를 입력해 주세요.")
	private String address;

	@NotBlank(message = "핸드폰 번호를 입력해 주세요.")
	private String phone;

	@SpecificValue(acceptedValues = "TRUE", message = "번호 중복 확인을 진행해 주세요.")
	private String isPhoneVerified;


	@NotNull(message = "회원 유형을 선택해 주세요.")
	private MemberType memberType;
}
