package com.sumit.todo.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.sumit.todo.mockito.service.TodoService;

public class TodoBusinessImplTestMockitoInjectMock {

	@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void testRetreiveTodosRelatedToSpring() {
		// Define the captor
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");
		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);
		List<String> todosForUser = todoBusinessImpl.retreiveTodosRelatedToSpring("Sumit");
		verify(todoServiceMock).retreiveTodos(stringArgumentCaptor.capture());
		assertEquals("Sumit", stringArgumentCaptor.getValue());
		assertEquals(2, todosForUser.size());
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring() {
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");
		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Sumit");
		verify(todoServiceMock).deleteTodo("Learn to dance");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");

	}

	@Test
	public void testArgumentCapture() {
		// Declare Argument Captor
		// Define Argument captor on specific method call
		// Capture Argument

		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");
		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Sumit");
		verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());
		assertEquals("Learn to dance", stringArgumentCaptor.getValue());
	}

	@Test
	public void testArgumentCaptureMultipleTimes() {
		// Declare Argument Captor
		// Define Argument captor on specific method call
		// Capture Argument

		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		List<String> todos = Arrays.asList("Learn Spring", "Learn to love", "Learn to dance");
		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Sumit");
		verify(todoServiceMock, times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertEquals(2, stringArgumentCaptor.getAllValues().size());
	}
}