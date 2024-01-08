package com.dannyhromau.quote;

import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.repository.UserRepository;
import com.dannyhromau.quote.service.impl.QuoteServiceImpl;
import com.dannyhromau.quote.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DisplayName("Testing of task_service")
@SpringBootTest(classes = MyQuoteApp.class)
@TestPropertySource("classpath:application-test.yml")
public class QuoteServiceImplTest {
    @InjectMocks
    private QuoteServiceImpl quoteService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;




    @Test
    @DisplayName("get top quotes when exists")
    void getTopQuotesWhenExists() {
        List<Quote> topQuotes = quoteService.getTopQuotes(Pageable.ofSize(10));
        int expectedSize = 10;
        int actualSize = topQuotes.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
}
