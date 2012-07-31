package com.piragua.sakila



import org.junit.*
import grails.test.mixin.*

@TestFor(FilmListController)
@Mock(FilmList)
class FilmListControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/filmList/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.filmListInstanceList.size() == 0
        assert model.filmListInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.filmListInstance != null
    }

    void testSave() {
        controller.save()

        assert model.filmListInstance != null
        assert view == '/filmList/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/filmList/show/1'
        assert controller.flash.message != null
        assert FilmList.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/filmList/list'


        populateValidParams(params)
        def filmList = new FilmList(params)

        assert filmList.save() != null

        params.id = filmList.id

        def model = controller.show()

        assert model.filmListInstance == filmList
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/filmList/list'


        populateValidParams(params)
        def filmList = new FilmList(params)

        assert filmList.save() != null

        params.id = filmList.id

        def model = controller.edit()

        assert model.filmListInstance == filmList
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/filmList/list'

        response.reset()


        populateValidParams(params)
        def filmList = new FilmList(params)

        assert filmList.save() != null

        // test invalid parameters in update
        params.id = filmList.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/filmList/edit"
        assert model.filmListInstance != null

        filmList.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/filmList/show/$filmList.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        filmList.clearErrors()

        populateValidParams(params)
        params.id = filmList.id
        params.version = -1
        controller.update()

        assert view == "/filmList/edit"
        assert model.filmListInstance != null
        assert model.filmListInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/filmList/list'

        response.reset()

        populateValidParams(params)
        def filmList = new FilmList(params)

        assert filmList.save() != null
        assert FilmList.count() == 1

        params.id = filmList.id

        controller.delete()

        assert FilmList.count() == 0
        assert FilmList.get(filmList.id) == null
        assert response.redirectedUrl == '/filmList/list'
    }
}
