package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.quiz.vo.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriterServiceTest {

	@Test
	public void writeAsStringTeset() {
		List<Answer> list = new ArrayList<>();
		List<String>optionList1=new ArrayList<>();
		optionList1.add("aaa");
		list.add(new Answer(1,optionList1));
		
		List<String>optionList2=new ArrayList<>();
		optionList2.add("bbb");
		list.add(new Answer(2,optionList2));
		
		List<String>optionList3=new ArrayList<>();
		optionList3.add("ccc");
		list.add(new Answer(3,optionList3));
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(list));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
