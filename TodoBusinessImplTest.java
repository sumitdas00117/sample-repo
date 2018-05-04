package com.sumit.todo.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.sumit.todo.mockito.service.TodoService;

public class TodoBusinessImplTest {

	@Test
	public void testRetreiveTodosRelatedToSpring() {
		// Define the captor
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");

		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);

		List<String> todosForUser = todoBusinessImpl.retreiveTodosRelatedToSpring("Sumit");

		verify(todoServiceMock).retreiveTodos(captor.capture());
		assertEquals("Sumit", captor.getValue());
		assertEquals(2, todosForUser.size());
	}

	@Test
	public void testRetreiveTodosRelatedToSpringUsingBDD() { // Given
		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");

		given(todoServiceMock.retreiveTodos("Sumit")).willReturn(todos);

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring() {
		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
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

		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = Arrays.asList("Learn Spring", "Learn Spring mvc", "Learn to dance");

		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Sumit");
		verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());
		// verify(todoServiceMock,never()).deleteTodo("Learn Spring");*/
		assertEquals("Learn to dance", stringArgumentCaptor.getValue());

	}

	@Test
	public void testArgumentCaptureMultipleTimes() {
		// Declare Argument Captor
		// Define Argument captor on specific method call
		// Capture Argument

		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = Arrays.asList("Learn Spring", "Learn to love", "Learn to dance");

		when(todoServiceMock.retreiveTodos("Sumit")).thenReturn(todos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Sumit");
		verify(todoServiceMock, times(2)).deleteTodo(stringArgumentCaptor.capture());
		// verify(todoServiceMock,never()).deleteTodo("Learn Spring");*/
		assertEquals(2, stringArgumentCaptor.getAllValues().size());

	}
}
