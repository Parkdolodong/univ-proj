package com.example.begin.service;

import com.example.begin.dto.FoodResDto;
import com.example.begin.naver.NaverClient;
import com.example.begin.naver.dto.ImageSearchReqDto;
import com.example.begin.naver.dto.ImageSearchResDto;
import com.example.begin.naver.dto.LocalSearchReqDto;
import com.example.begin.naver.dto.LocalSearchResDto;
import com.example.begin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NaverService {

    private final NaverClient naverClient;
    //private final UserRepository userRepository;

    public LocalSearchResDto findLocalSearch(LocalSearchReqDto reqDto) {
        return naverClient.localSearch(reqDto);
    }
    public ImageSearchResDto findImageSearch(ImageSearchReqDto reqDto) {
        return naverClient.imageSearch(reqDto);
    }

    public FoodResDto search(String query) {

        var foodResDto = FoodResDto.builder().build();

        /*
        * naver local search area
        * */
        var localSearchDto = LocalSearchReqDto.builder().query(query).build();
        var localResult = naverClient.localSearch(localSearchDto);
        var optItem =  localResult.getItems().stream().findFirst();

        if(optItem.isPresent()) {
            var item = optItem.get();
            var tmp = item.getTitle().replaceAll("<[^>]*>","");
            foodResDto.setTitle(tmp);
            foodResDto.setCategory(item.getCategory());
            foodResDto.setAddress(item.getAddress());
            foodResDto.setRoadAddress(item.getRoadAddress());
            foodResDto.setHomepageLink(item.getLink());


            /*
             * naver image search area
             * */

            var imageSerachDto = ImageSearchReqDto.builder().query(item.getTitle()).build();
            var imageResult = naverClient.imageSearch(imageSerachDto);
            var optImageItem =  imageResult.getItems().stream().findFirst();

            if(optImageItem.isPresent()) {

                foodResDto.setImageLink(optImageItem.get().getThumbnail());

            }
        }

        log.info("{} search result = ", foodResDto);

        return foodResDto;
    }
}
