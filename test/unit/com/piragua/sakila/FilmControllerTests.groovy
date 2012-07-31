package com.piragua.sakila



import org.junit.*
import grails.test.mixin.*

@TestFor(FilmController)
@Mock(Film)
class FilmControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/film/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.filmInstanceList.size() == 0
        assert model.filmInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.filmInstance != null
    }

    void testSave() {
        controller.save()

        assert model.filmInstance != null
        assert view == '/film/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/film/show/1'
        assert controller.flash.message != null
        assert Film.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/film/list'


        populateValidParams(params)
        def film = new Film(params)

        assert film.save() != null

        params.id = film.id

        def model = controller.show()

        assert model.filmInstance == film
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/film/list'


        populateValidParams(params)
        def film = new Film(params)

        assert film.save() != null

        params.id = film.id

        def model = controller.edit()

        assert model.filmInstance == film
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/film/list'

        response.reset()


        populateValidParams(params)
        def film = new Film(params)

        assert film.save() != null

        // test invalid parameters in update
        params.id = film.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/film/edit"
        assert model.filmInstance != null

        film.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/film/show/$film.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        film.clearErrors()

        populateValidParams(params)
        params.id = film.id
        params.version = -1
        controller.update()

        assert view == "/film/edit"
        assert model.filmInstance != null
        assert model.filmInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/film/list'

        response.reset()

        populateValidParams(params)
        def film = new Film(params)

        assert film.save() != null
        assert Film.count() == 1

        params.id = film.id

        controller.delete()

        assert Film.count() == 0
        assert Film.get(film.id) == null
        assert response.redirectedUrl == '/film/list'
    }
}
