package org.springside.examples.bootapi.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.examples.bootapi.BootApiApplication;
import org.springside.examples.bootapi.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApiApplication.class)
@DirtiesContext
public class BookDaoTest_OE25Dev {

	@Autowired
	private BookDao bookDao;

	@Test
	public void findByOwnerId_1_oe() {
		List<Book> books = bookDao.findByOwnerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(2);
	}

	@Test
	public void findByOwnerId_2_oe() {
		List<Book> books = bookDao.findByOwnerId(1L, new PageRequest(0, 10));
		// removed other assertion
		assertThat(books.get(0).title).isEqualTo("Big Data日知录");
	}

	@Test
	public void findByBorrowerId_1_oe() {
		List<Book> books = bookDao.findByBorrowerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(0);
	}

}
