/*
날짜 : 2023/02/10
이름 : 최아영
내용 : Kmarket2 STS Cs_Cate1VO
*/

package kr.co.kmarket2.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cs_Cate1VO {

	private int cate1;
	private String c1Name;
	
	// 추가필드
    private int cate2;
    private String c2Name;
    
   
}
