package com.daftmobile.android4beginners4.robots.model


class ListRobotsDataSource: RobotsDataSource {
    private val robots = mutableListOf<Robot>()

    override fun getRobots() = robots.toList()

    override fun addNew(robot: Robot) {
        robots.add(robot)

    }
//
    fun sortAsc() : List<Robot>{
       return robots.toList().sortedBy { it.name }
    }


    fun sortDsc() : List<Robot>{
        return robots.toList().sortedBy { it.name }.reversed()
    }

}
