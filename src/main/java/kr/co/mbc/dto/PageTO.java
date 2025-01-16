package kr.co.mbc.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class PageTO<T> implements Page<T> {
	private int page;
	private List<T> content;
	private final int pageSize = 5;
	private int begin;
	private int end;
	
	public PageTO(int page, List<T> content) {
		super();
		this.page = page;
		this.content = content;
		this.begin = (page-1)*pageSize;
		
		this.end = begin + (pageSize-1);//2+(2-1) =3
		this.end = (int) (getTotalElements( ) > end ? end : getTotalElements( )-1);

		
	}
	
	
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return page;
	}
	
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
			
		return content.size();
	}
	
	
	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public List<T> getContent() {
		// TODO Auto-generated method stub
		
		List<T> list = new ArrayList<T>();
		
		for(int i= begin;i<end+1;i++) {
			T t = content.get(i);
			
			list.add(t);
		}
		
		return list;
	}
	
	
	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		
		return content.size()>0? true : false;
	}
	
	
	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return Sort.by("id");
	}
	
	
	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean isLast() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return getTotalPages()>page? true: false;
	}
	
	
	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return page > 1 ? true: false;
	}
	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return getContent().iterator();
	}
	
	
	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		return (int) (((getTotalElements()-1)/pageSize) +1);
	}
	
	
	
	@Override
	public long getTotalElements() {
		
		return content.size();
	}
	
	
	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> converter) {
		// TODO Auto-generated method stub
		return null;
	}



	


}
