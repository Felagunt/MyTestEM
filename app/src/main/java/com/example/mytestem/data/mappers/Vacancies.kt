package com.example.mytestem.data.mappers

import com.example.mytestem.data.dto.AddressDto
import com.example.mytestem.data.dto.ExperienceDto
import com.example.mytestem.data.dto.SalaryDto
import com.example.mytestem.data.dto.VacancyDto
import com.example.mytestem.domain.models.Address
import com.example.mytestem.domain.models.Experience
import com.example.mytestem.domain.models.Salary
import com.example.mytestem.domain.models.Vacancy

fun VacancyDto.toVacancy(): Vacancy {
    return Vacancy(
        address = address.toAddress(),
        appliedNumber = appliedNumber,
        company = company,
        description = description,
        experience = experience.toExperience(),
        id = id,
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishedDate,
        questions = questions,
        responsibilities = responsibilities,
        salary = salary.toSalary(),
        schedules = schedules,
        title = title
    )
}

fun AddressDto.toAddress(): Address {
    return Address(
        house = house,
        street = street,
        town = town
    )
}

fun ExperienceDto.toExperience(): Experience {
    return Experience(
        previewText = previewText,
        text = text
    )
}

fun SalaryDto.toSalary(): Salary {
    return Salary(
        full = full,
        short = short
    )
}