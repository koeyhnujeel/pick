// package com.zunza.pick;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
//
// import com.zunza.pick.member.entity.MemberType;
// import com.zunza.pick.member.entity.Member;
// import com.zunza.pick.member.repository.MemberRepository;
// import com.zunza.pick.product.entity.Category;
// import com.zunza.pick.product.entity.Product;
// import com.zunza.pick.product.entity.ProductImage;
// import com.zunza.pick.product.entity.ProductStatus;
// import com.zunza.pick.product.repository.ProductImageRepository;
// import com.zunza.pick.product.repository.ProductRepository;
//
// import lombok.RequiredArgsConstructor;
//
// @Component
// @RequiredArgsConstructor
// public class DataInit implements CommandLineRunner {
//
// 	private final MemberRepository memberRepository;
// 	private final PasswordEncoder passwordEncoder;
// 	private final ProductRepository productRepository;
// 	private final ProductImageRepository productImageRepository;
//
// 	@Value("${test.mainImageUrl}")
// 	private String mainImageUrl;
//
// 	@Value("${test.detailImageUrl}")
// 	private String detailImageUrl;
//
// 	@Override
// 	public void run(String... args) throws Exception {
// 		List<Member> members = new ArrayList<>();
//
// 		Member customer = Member.builder()
// 			.email("customer@email.com")
// 			.password(passwordEncoder.encode("password"))
// 			.name("김고객")
// 			.nickname("best customer")
// 			.address("서울 강남")
// 			.phone("010-1234-5678")
// 			.memberType(MemberType.CUSTOMER)
// 			.build();
//
// 		Member seller = Member.builder()
// 			.email("seller@email.com")
// 			.password(passwordEncoder.encode("password"))
// 			.name("김판매")
// 			.nickname("best seller")
// 			.address("서울 강남")
// 			.phone("010-1234-5671")
// 			.memberType(MemberType.SELLER)
// 			.build();
//
// 		Member admin = Member.builder()
// 			.email("admin@email.com")
// 			.password(passwordEncoder.encode("password"))
// 			.name("김관리")
// 			.nickname("best admin")
// 			.address("서울 강남")
// 			.phone("010-1232-5671")
// 			.memberType(MemberType.ADMIN)
// 			.build();
//
// 		members.add(customer);
// 		members.add(seller);
// 		members.add(admin);
//
// 		memberRepository.saveAll(members);
//
// 		for (int i = 1; i < 101; i++) {
// 			Product product = Product.builder()
// 				.name("옷" + i)
// 				.price(20000 + i * 100)
// 				.description("옷 입니다~" + i)
// 				.stock(i)
// 				.category(Category.TOP)
// 				.status(ProductStatus.IN_STOCK)
// 				.build();
// 			Product saved = productRepository.save(product);
//
// 			ProductImage mainImage = ProductImage.of(saved, mainImageUrl);
// 			ProductImage detailImage = ProductImage.of(saved, detailImageUrl);
// 			productImageRepository.save(mainImage);
// 			productImageRepository.save(detailImage);
// 		}
//
// 		for (int i = 1; i < 101; i++) {
// 			Product product = Product.builder()
// 				.name("옷" + (i + 100))
// 				.price(20000 + i * 100)
// 				.description("옷 입니다~" + (i + 100))
// 				.stock(i)
// 				.category(Category.TOP)
// 				.status(ProductStatus.IN_STOCK)
// 				.build();
// 			Product saved = productRepository.save(product);
//
// 			ProductImage mainImage = ProductImage.of(saved, mainImageUrl);
// 			ProductImage detailImage = ProductImage.of(saved, detailImageUrl);
// 			productImageRepository.save(mainImage);
// 			productImageRepository.save(detailImage);
//
// 		}
// 	}
// }
