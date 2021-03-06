package com.insta.sns.service;

import java.util.List;
import java.util.function.Supplier;

<<<<<<< HEAD
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

=======
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insta.sns.config.auth.dto.LoginUser;
<<<<<<< HEAD
=======
import com.insta.sns.config.handler.ex.MyUserIdNotFoundException;
import com.insta.sns.domain.image.Image;
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
import com.insta.sns.domain.image.ImageRepository;
import com.insta.sns.domain.user.User;
import com.insta.sns.domain.user.UserRepository;
import com.insta.sns.web.dto.JoinReqDto;
import com.insta.sns.web.dto.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@PersistenceContext
	EntityManager em;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public void 회원가입(JoinReqDto joinReqDto) {
		String encPassword = 
				bCryptPasswordEncoder.encode(joinReqDto.getPassword());
		joinReqDto.setPassword(encPassword);
		userRepository.save(joinReqDto.toEntity());
	}
	
	// 읽기 전용 트랜잭션
<<<<<<< HEAD
	// (1) 변경 감지 연산을 하지 않음.
	// (2) isolation(고립성)을 위해 Phantom read 문제가 일어나지 않음.
	@Transactional(readOnly = true)
	public UserProfileRespDto 회원프로필(int id, LoginUser loginUser) {
		
		int imageCount;
		int followerCount;
		int followingCount;
		
		User userEntity = userRepository.findById(id)
				.orElseThrow(new Supplier<MyUserIdNotFoundException>() {
					@Override
					public MyUserIdNotFoundException get() {
						return new MyUserIdNotFoundException();
					}
				});
		
		String q = "select im.id as id, im.imageUrl as imageUrl, im.userId as userId, (select count(*) from likes lk where lk.imageId = im.id) as likeCount, (select count(*) from comment ct where ct.imageId = im.id) as commentCount from image im where im.userId = 1";
		Query query = em.createNativeQuery(q, "UserProfileImageRespDtoMapping");
		List<UserProfileImageRespDto> imagesEntity = query.getResultList();

		imageCount = imagesEntity.size();

		// 2. 팔로우 수 (수정해야됨)
		followerCount = 50;
		followingCount = 100;
		
		// 3. 최종마무리
		UserProfileRespDto userProfileRespDto = 
				UserProfileRespDto.builder()
				.pageHost(id==loginUser.getId())
				.followerCount(followerCount)
				.followingCount(followingCount)
				.imageCount(imageCount)
				.user(userEntity)
				.images(imagesEntity) // 수정완료(Dto만듬) (댓글수, 좋아요수)
				.build();

		return userProfileRespDto;
	}
=======
		// (1) 변경 감지 연산을 하지 않음.
		// (2) isolation(고립성)을 위해 Phantom read 문제가 일어나지 않음.
		@Transactional(readOnly = true)
		public UserProfileRespDto 회원프로필(int id, LoginUser loginUser) {

			int imageCount;
			int followerCount;
			int followingCount;

			User userEntity = userRepository.findById(id)
					.orElseThrow(new Supplier<MyUserIdNotFoundException>() {
						@Override
						public MyUserIdNotFoundException get() {
							return new MyUserIdNotFoundException();
						}
					});

			// 1. 이미지들과 전체 이미지 카운트
			List<Image> imagesEntity = imageRepository.findByUserId(id);
			imageCount = imagesEntity.size();

			// 2. 팔로우 수 (수정해야됨)
			followerCount = 50;
			followingCount = 100;

			// 3. 최종마무리
			UserProfileRespDto userProfileRespDto = 
					UserProfileRespDto.builder()
					.pageHost(id==loginUser.getId())
					.followerCount(followerCount)
					.followingCount(followingCount)
					.imageCount(imageCount)
					.user(userEntity)
					.images(imagesEntity)
					.build();

			return userProfileRespDto;
		}
>>>>>>> 6ce0676d695307c2bf7f54537e029d687aa873fb
}

