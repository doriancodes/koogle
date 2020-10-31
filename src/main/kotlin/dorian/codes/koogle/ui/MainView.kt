package dorian.codes.koogle.ui

import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.PageService

@Route("")
class MainView(private val pageService: PageService) : VerticalLayout() {
    private val grid = Grid(Page::class.java)
    private fun configureGrid() {
        grid.addClassName("page-grid")
        grid.setSizeFull()
        grid.setColumns("url", "title", "text", "slug")
    }

    private fun updateList() {
        val pages: List<Page> = pageService.findAll() as List<Page>
        grid.setItems(pages)
    }

    init {
        addClassName("list-view")
        setSizeFull()
        configureGrid()
        add(grid)
        updateList()
    }
}