package com.octawizard.domain.model

enum class WeekDay(val dayNumber: Int, val photo: String) {
    Monday(1, photo = "https://i.pinimg.com/originals/89/e9/ca/89e9ca5f453f93f6dbf46f4fd1d44c7a.png"),
    Tuesday(2, photo = "https://logos.textgiraffe.com/logos/logo-name/Tuesday-designstyle-summer-m.png"),
    Wednesday(3, photo = "https://www.pngitem.com/pimgs/m/242-2422406_wednesday-logo-design-png-sunday-png-transparent-png.png"),
    Thursday(4, photo = "https://e7.pngegg.com/pngimages/836/184/png-clipart-nifty-50-bank-logo-investor-thursday-text-computer-wallpaper.png"),
    Friday(5, photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTotbsXvir_MDc9NbSx-YyN-JFt2yXLllxjMQ&usqp=CAU"),
    Saturday(6, photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTwFU3O6DxX1vGeluMLCULbk5wVTyoKjMJdPA&usqp=CAU"),
    Sunday(7, photo = "https://logos.flamingtext.com/Name-Logos/Sunday-design-sketch-name.png")
}

val WeekDays: List<WeekDay> = WeekDay.values().toList()
