package com.jdong.studycafe.favorites.service;

import com.jdong.studycafe.beverages.exception.BeverageNotFoundException;
import com.jdong.studycafe.cafes.exception.CafeNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.favorites.exception.FavoriteDuplicatedException;
import com.jdong.studycafe.favorites.exception.FavoriteNotFoundException;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.favorites.repository.FavoriteQuerydslRepository;
import com.jdong.studycafe.favorites.repository.FavoriteRepository;
import com.jdong.studycafe.members.exception.MemberNotFoundException;
import com.jdong.studycafe.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.channels.FileLockInterruptionException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.jdong.studycafe.common.error.exception.ErrorCode.FAVORITE_DUPLICATION;

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
        Optional<Favorite> byId = favoriteRepository.findById(favoriteId);
        byId.orElseThrow(() -> new FavoriteNotFoundException(favoriteId));

        Favorite deleted = favoriteRepository.findByFavoriteId(favoriteId);
        String msg = "";
        if (deleted.getMember().getId() == memberId) {
            favoriteRepository.deleteById(favoriteId);
            msg = "delete Favorite success.";
        } else {
            throw new MemberNotFoundException(memberId);
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
        List<Favorite> favorites = favoriteRepository.findByFavorite(memberId, favoriteRequestDTO.getCafeId(), favoriteRequestDTO.getBeverageId());
        if (favorites.size() > 0) {
            throw new FavoriteDuplicatedException(favorites.get(0).getId().toString());
        }

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()->new MemberNotFoundException(memberId));
        Optional<Cafe> optionalCafe = cafeRepository.findById(favoriteRequestDTO.getCafeId());
        Cafe cafe = optionalCafe.orElseThrow(() -> new CafeNotFoundException(favoriteRequestDTO.getCafeId()));
        Optional<Beverage> optionalBeverage = beverageRepository.findById(favoriteRequestDTO.getBeverageId());
        Beverage beverage = optionalBeverage.orElseThrow(() -> new BeverageNotFoundException(favoriteRequestDTO.getBeverageId()));


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
                .beverageName(saved.getBeverage().getName())
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
