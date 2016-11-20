package com.epam.jmp.library.rest.resource.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.service.LibraryService;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryResourceImplTest extends JerseyTest {

    @Mock private LibraryService libraryService;
    @InjectMocks private LibraryResourceImpl libraryResource;

    @Override
    protected Application configure() {
        return new Application() {
            @Override
            public Set<Object> getSingletons() {
                return Collections.singleton(libraryResource);
            }
        };
    }

    @Test
    public void getAllLibrariesResourceReturnsAllAvailableLibraries() throws Exception {
        Library library1 = new Library("Library 1");
        Library library2 = new Library("Library 2");
        when(libraryService.getAllLibraries()).thenReturn(Arrays.asList(library1, library2));
        List<?> libraries = target("libraries").request().get(List.class);
        assertNotNull(libraries);
        assertEquals(2, libraries.size());
    }

    @Test
    public void getLibraryResourceReturnsCorrectLibrary() throws Exception {
        Library library = new Library("Library 1");
        library.setId(1L);
        library.setDescription("Test Library");
        when(libraryService.getLibrary(1L)).thenReturn(library);
        Library resourceLibrary = target("libraries/1").request().get(Library.class);
        assertEquals(library.getId(), resourceLibrary.getId());
        assertEquals(library.getName(), resourceLibrary.getName());
        assertEquals(library.getDescription(), resourceLibrary.getDescription());
    }

    @Test
    public void getLibraryResourceShouldReturnNotFoundStatusWhenLibraryDoesNotExist() throws Exception {
        when(libraryService.getLibrary(1L)).thenReturn(null);
        Response response = target("libraries/1").request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void updateLibraryResourceShouldUpdateExistingLibrary() throws Exception {
        Library updateLibrary = new Library("Test");
        Entity<Library> libraryEntity = Entity.entity(updateLibrary, MediaType.APPLICATION_JSON);
        when(libraryService.getLibrary(1L)).thenReturn(new Library());
        target("libraries/1").request().put(libraryEntity);
        verify(libraryService).updateLibrary(any(Library.class));
    }

    @Test
    public void updateLibraryResourceShouldReturnNotFoundStatusWhenLibraryDoesNotExist() throws Exception {
        Entity<Library> library = Entity.entity(new Library("Test"), MediaType.APPLICATION_JSON);
        when(libraryService.getLibrary(1L)).thenReturn(null);
        Response response = target("libraries/1").request().put(library);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteLibraryResourceShouldDeleteExistingLibrary() throws Exception {
        when(libraryService.getLibrary(1L)).thenReturn(new Library());
        target("libraries/1").request().delete();
        verify(libraryService).deleteLibrary(1L);
    }

    @Test
    public void deleteLibraryResourceShouldReturnNotFoundStatusWhenLibraryDoesNotExist() throws Exception {
        when(libraryService.getLibrary(1L)).thenReturn(null);
        Response response = target("libraries/1").request().delete();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}