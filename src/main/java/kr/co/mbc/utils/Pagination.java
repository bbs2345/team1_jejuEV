package kr.co.mbc.utils;

import kr.co.mbc.dto.Criteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pagination {
	
	private Long displayPageNum;
	
	private Long endPage;
	
	private Long startPage;
	
	private Long totalPages;
	
	private boolean prev;
	private boolean next;
	
	public Pagination(Criteria criteria, Long totalCount) {
		this.displayPageNum = 10L;
		this.endPage = (long) (Math.ceil( (double) criteria.getPage() / (double) displayPageNum )) * displayPageNum;
		this.startPage = endPage - displayPageNum + 1;
		this.totalPages = (long) (Math.ceil((double) totalCount / (double) criteria.getPerPageContent()));
		this.prev = startPage > 1;
		this.next = endPage < totalPages;
		
		if (endPage > totalPages) {
			endPage = totalPages;
		}
	}
	
}
