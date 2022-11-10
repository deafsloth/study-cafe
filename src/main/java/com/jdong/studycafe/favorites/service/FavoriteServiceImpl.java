package com.jdong.studycafe.favorites.service;

import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.Members.domain.Member;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.favorites.repository.FavoriteQuerydslRepository;
import com.jdong.studycafe.favorites.repository.FavoriteRepository;
import com.jdong.studycafe.Members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final CafeRepository cafeRepository;
    private final MemberRepository memberRepository;
    private final BeverageRepository beverageRepository;
    private final FavoriteQuerydslRepository favoriteQuerydslRepository;


    @Override
    public HashMap<String, Object> deleteFavorite(Long favoriteId, Long memberId) {
        Favorite deleted = favoriteRepository.findByFavoriteId(favoriteId);
        String msg = "";
        if (deleted.getMember().getId() == memberId) {
            favoriteRepository.deleteById(favoriteId);
            msg = "delete Favorite success.";
        } else {
            msg = "not a valid request.";
        }
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .favoriteId(deleted.getId())
                .cafeId(deleted.getCafe().getId())
                .cafeName(deleted.getCafe().getName())
                .beverageId(deleted.getBeverage().getId())
                .beverageImage(deleted.getBeverage().getMainImageUrl())
                .beverageName(deleted.getBeverage().getName())
                .price(deleted.getBeverage().getPrice())
                .createTime(deleted.getCreatedDate())
                .modifiedTime(deleted.getModifiedDate())
                .build();

        HashMap<String, Object> result = new HashMap<>();
        result.put("deletedFavorite", favoriteDTO);
        result.put("message", msg);

        return result;
    }

    @Override
    public FavoriteDTO addFavorite(FavoriteRequestDTO favoriteRequestDTO, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow();
        Optional<Cafe> optionalCafe = cafeRepository.findById(favoriteRequestDTO.getCafeId());
        Cafe cafe = optionalCafe.orElseThrow();
        Optional<Beverage> optionalBeverage = beverageRepository.findById(favoriteRequestDTO.getBeverageId());
        Beverage beverage = optionalBeverage.orElseThrow();

        Favorite favorite = Favorite.builder()
                .member(member)
                .beverage(beverage)
                .cafe(cafe)
                .build();
        Favorite saved = favoriteRepository.save(favorite);

        FavoriteDTO result = FavoriteDTO.builder()
                .favoriteId(saved.getId())
                .cafeId(saved.getCafe().getId())
                .cafeName(saved.getCafe().getName())
                .beverageId(saved.getBeverage().getId())
                .beverageImage(saved.getBeverage().getMainImageUrl())
                .price(saved.getBeverage().getPrice())
                .createTime(saved.getCreatedDate())
                .modifiedTime(saved.getModifiedDate())
                .build();

        return result;
    }

    @Override
    public List<FavoriteDTO> getFavoriteListByMemberId(Long memberId) {
        List<FavoriteDTO> favoriteListByMemberId = favoriteQuerydslRepository.findFavoriteListByMemberId(memberId);

        return favoriteListByMemberId;
    }

}
