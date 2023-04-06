package com.example.begin.controller;

import com.example.begin.dto.Req;
import com.example.begin.dto.Res;
import com.example.begin.dto.ReviewDto;
import com.example.begin.dto.UserDto;
import com.example.begin.naver.dto.ImageSearchReqDto;
import com.example.begin.naver.dto.LocalSearchReqDto;
import com.example.begin.service.NaverService;
import com.example.begin.service.ReviewService;
import com.example.begin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping(value = "/apis")
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final NaverService naverService;
    private final UserService userService;
    private final ReviewService reviewService;

    /*
    * http://localhost:8080/apis/search/local?query=국밥
    * */
    @GetMapping(value = "/search/local")
    public ResponseEntity findLocalSearch(@RequestParam("query") String query) {
        var reqDto = LocalSearchReqDto.builder().query(query).build();
        return ResponseEntity.status(HttpStatus.OK).body(naverService.findLocalSearch(reqDto));
    }

    @GetMapping(value = "/search/image")
    public ResponseEntity findImageSearch(@RequestParam("query") String query) {
        var reqDto = ImageSearchReqDto.builder().query(query).build();
        return ResponseEntity.status(HttpStatus.OK).body(naverService.findImageSearch(reqDto));
    }

    @GetMapping(value = "/search")
    public ResponseEntity findSearch(@RequestParam("query") String query) {
        return ResponseEntity.status(HttpStatus.OK).body(naverService.search(query));
    }

    //http://localhost:8080/apis/hello
    //Get Method
    //return "hello" ;

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/path-variable/{name}")
    public String pathVariable(@PathVariable String name) {
        log.info("path name = {}", name);
        return name;
    }

    //HandsOn
    /*
    * pathvariable name을 받고
    * queryParam id 받는
    * get method 작성하라.
    *
    * http://localhost:8080/apis/path-ver/{name}?id=1234
    * */

    @GetMapping(value = "/path-ver/{name}")
    public String pathVer(@PathVariable String name, @RequestParam String id) {
        log.info("name-{}, id-{}", name, id);
        return "id :" + id + "name: "+ name;
    }

    @GetMapping(value = "/params")
    public String params(@RequestParam String name, @RequestParam int age) {
        return name + age;
    }

    @GetMapping(value = "/params2")
    public void params2(@RequestParam Map<String, String> queries) {

        //lamda expression
        queries.forEach((k, v)->{
            log.info("key={}, value={}",k, v);
        });

        //map
        queries.entrySet().forEach(e->{
            log.info("key={}, value={}",e.getKey(), e.getValue());

        });
    }

    // 아래방식으로 차리하는것을 정석을 합니다.
    @GetMapping(value = "/params3")
    public ResponseEntity params3(Req req) {
        log.info("Object Req = {}", req);
        return ResponseEntity.ok(req);

    }

    /*
    * Post Sections
    * */

    @PostMapping(value = "/post")
    public void post(@RequestBody Map<String, Object> reqData) {
        reqData.entrySet().forEach(e->{
            log.info("key={} value={}", e.getKey(), e.getValue());
        });
    }

    @PostMapping(value = "/post-obj")
    public ResponseEntity post2(@RequestBody Req req) {
        return ResponseEntity.ok(req);
    }

    /*
     * Put Sections
     * */
    @PutMapping(value = "/put/{userid}")
    public ResponseEntity Put(@RequestBody Req req) {
        log.info("Req = {}", req);
        var res = Res.builder().httpStatus(HttpStatus.OK.toString())
                .result("OK")
                .idx(1L)
                .name(req.getName())
                .build();
        return ResponseEntity.ok(res);
    }

    /*
    Hands On
    * 1. user_id를 통해 User정보를 출력
2. 닉네임을 통해 사용자를 조회
    1. QueryParam or PathVariable을 활용해 출력
3. 사용자를 Post방식으로 저장하고 저장된 사용자를 출력
4. user_id를 통해 해당사용자의 리뷰 정보를 출력
5. 특정사용자의 nick을 업데이트하고 정보를 출력
6. 특정 사용자의 맛집리스트를 출력
    * */

    @GetMapping("/user-info")
    public ResponseEntity findUserInfo(@RequestParam String userid) {
        log.info("user id = {}", userid);
        var dto = userService.findUserInfo(userid);
        if(dto.getIdx() != null) {
            dto.setResult("OK");
            return ResponseEntity.ok(dto);
        }
        dto.setResult("FAIL");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @PostMapping("/register")
    public ResponseEntity regiserUser(@RequestBody UserDto userDto) {
        log.info("save to user = {}", userDto);
        var dto = userService.registerUser(userDto);
        if(dto.getIdx() != null) {
            dto.setResult("OK");
            return ResponseEntity.ok(dto);
        }
        dto.setResult("FAIL");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @GetMapping("/{userid}/reviews")
    public ResponseEntity findReviews(@PathVariable(value = "userid") String userId) {
        log.info("review user id = {}", userId);

        var reviews = reviewService.findReviews(userId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/add/review")
    public ResponseEntity addReview(@RequestBody ReviewDto reviewDto) {
        log.info("save to review = {}", reviewDto);
        var dto = reviewService.addReview(reviewDto);
        if(dto.getIdx() != null) {
            dto.setResult("OK");
            return ResponseEntity.ok(dto);
        }
        dto.setResult("FAIL");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @PutMapping("/update/user")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        log.info("update user info = {}", userDto);
        return ResponseEntity.ok(userService.registerUser(userDto));
    }
}
