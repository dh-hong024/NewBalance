package practice.newbalance.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
@ToString
public class UserInfoDto {
    private String name;
    //전화번호 정보
    private Collection<String> prefixNumberList;
    private Collection<String> telecoms;
    private String prefixNumber;
    private String number;
    private String suffixNumber;

    //성별정보
    private Collection<String> genderList;
    private String gender;

    //생년월일 정보
    private String year;
    private String month;
    private String day;

    public UserInfoDto(){

        //전화정보 리스트 세팅
        Map<String, String> prefixNumber = new LinkedHashMap<>();
        prefixNumber.put("010", "010");
        prefixNumber.put("011", "011");
        prefixNumber.put("017", "017");
        prefixNumber.put("016", "016");
        prefixNumber.put("019", "019");

        Map<String, String> telecoms = new LinkedHashMap<>();
        telecoms.put("SKT", "SKT");
        telecoms.put("KT", "KT");
        telecoms.put("LG", "LG U+");

        Map<String, String> genders = new LinkedHashMap<>();
        genders.put("male", "남자");
        genders.put("female", "여자");

        this.telecoms = telecoms.values();
        this.prefixNumberList = prefixNumber.values();
        this.genderList = genders.values();
    }

    public String getPhoneNumber(){
        return prefixNumber + "-" + number + "-" + suffixNumber;
    }

    public String getYearOfBirth(){
        return year + month + day;
    }

}
