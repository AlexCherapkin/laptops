package com.example.laptops.data

import com.example.laptops.model.Product
import com.example.laptops.R

object DataSource {

    val products1 = listOf(
        Product("Ноутбук Apple MacBook Air 13 Late 2020", "Ноутбук Apple MacBook Air 13 Late являются одной из самых интригующих новинок «яблочной» компании.", R.drawable.lap1,94990),
        Product("Ноутбук Apple MacBook Pro 16 Late 2019", "Intel Core i7 2.60 ГГц, 16 ГБ DDR4 2666 МГц", R.drawable.lap2,234000),
        Product("Ноутбук Apple MacBook Pro 13 Mid 2020 ", "Лёгкий и при этом очень мощный, MacBook Pro обладает самым ярким экраном и лучшей цветопередачей среди всех линейки", R.drawable.lap3,170000)
    )

    val products2 = listOf(
        Product("Ноутбук ASUS Laptop 15 X509FA-BR949T", "1366x768, Intel Core i3 2.1 ГГц, RAM 4 ГБ, SSD 256 ГБ, Win10 Home", R.drawable.lap4,36000),
        Product("Ноутбук ASUS ExpertBook B1 B1400CEAE-EB0465T ", "1920x1080, Intel Core i3 3 ГГц, RAM 8 ГБ, SSD 256 ГБ, Win10 Home), 90NX0421-M07010, черный", R.drawable.lap5,47000),
        Product("Ноутбук ASUS E210MA-GJ004T", "1366x768, Intel Pentium Silver 1.1 ГГц, RAM 4 ГБ, eMMC 64 ГБ, Win10 Home), 90NB0R41-M05420, синий", R.drawable.lap6,26000)
    )

    val products3 = listOf(
        Product("Ноутбук DELL Precision 15 3561", "Диагональ экрана: 15.6\"; Разрешение экрана: 1920x1080; Поверхность экрана: антибликовая; ", R.drawable.lap7,115000),
        Product("Ноутбук DELL Inspiron 7400", "(2560x1600, Intel Core i5 2.4 ГГц, RAM 8 ГБ, SSD 512 ГБ, Win10 Home), 7400-8532, platinum silver", R.drawable.lap8,88000),
        Product("Ноутбук DELL Vostro 3400", "(1920x1080, Intel Core i5 2.4 ГГц, RAM 4 ГБ, HDD 1000 ГБ, Win10 Home), 3400-7503, черный", R.drawable.lap9,170000)
    )
}