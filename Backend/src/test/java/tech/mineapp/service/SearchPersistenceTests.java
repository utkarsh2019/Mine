package tech.mineapp.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import tech.mineapp.constants.Category;
import tech.mineapp.entity.SearchEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.SearchRepository;
import tech.mineapp.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SearchPersistenceTests {

	@Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    SearchPersistenceService searchPersistenceService;
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchRepository searchRepository;
    
    @Test
    public void testPersistSearchDetails() throws SQLException {
    	Long testUserId = (long) 1;
    	Category testCategory = Category.movie;
    	String testQuery = "test_query";
    	
    	UserEntity testUserEntity = new UserEntity();
    	
    	testUserEntity.setUserId(testUserId);
    	
    	SearchEntity testSearchEntity = new SearchEntity();
    	
    	when(userRepository.findUserByUserId(eq(testUserId))).thenReturn(Optional.of(testUserEntity));
    	when(searchRepository.findSearchEntityByUserAndCategoryAndQuery(any(), any(), any())).thenReturn(Optional.of(testSearchEntity));
    	
    	searchPersistenceService.persistSearchDetails(testUserId, testCategory, testQuery);
    	
    	verify(searchRepository, times(1)).findSearchEntityByUserAndCategoryAndQuery(eq(testUserEntity), eq(testCategory), eq(testQuery));
    }	
}
