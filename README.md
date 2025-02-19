# Property View API

## Описание
**Property View API** — это RESTful API для работы с отелями. Оно предоставляет возможность управлять списком отелей, получать их краткую и детальную информацию, искать отели по параметрам, добавлять удобства (amenities) и получать гистограмму распределения по различным категориям.

## Функциональность

### 1. Получение списка всех отелей
**Метод:** `GET /property-view/hotels`

**Пример ответа:**
```json
[
    {
        "id": 1,
        "name": "DoubleTree by Hilton Minsk",
        "description": "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...",
        "address": "9 Pobediteley Avenue, Minsk, 220004, Belarus",
        "phone": "+375 17 309-80-00"
    }
]
```

### 2. Получение информации о конкретном отеле
**Метод:** `GET /property-view/hotels/{id}`

**Пример ответа:**
```json
{
    "id": 1,
    "name": "DoubleTree by Hilton Minsk",
    "brand": "Hilton",
    "address": {
        "houseNumber": 9,
        "street": "Pobediteley Avenue",
        "city": "Minsk",
        "county": "Belarus",
        "postCode": "220004"
    },
    "contacts": {
        "phone": "+375 17 309-80-00",
        "email": "doubletreeminsk.info@hilton.com"
    },
    "arrivalTime": {
        "checkIn": "14:00",
        "checkOut": "12:00"
    },
    "amenities": [
        "Free parking",
        "Free WiFi",
        "Non-smoking rooms",
        "Concierge",
        "On-site restaurant",
        "Fitness center",
        "Pet-friendly rooms",
        "Room service",
        "Business center",
        "Meeting rooms"
    ]
}
```

### 3. Поиск отелей по параметрам
**Метод:** `GET /property-view/search?param=value`

Параметры: `name`, `brand`, `city`, `county`, `amenities`.

**Пример:** `/property-view/search?city=minsk`

**Ответ:** аналогичен `GET /property-view/hotels`

### 4. Добавление нового отеля
**Метод:** `POST /property-view/hotels`

**Пример запроса:**
```json
{
    "name": "DoubleTree by Hilton Minsk",
    "description": "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms...",
    "brand": "Hilton",
    "address": {
        "houseNumber": "9",
        "street": "Pobediteley Avenue",
        "city": "Minsk",
        "county": "Belarus",
        "postCode": "220004"
    },
    "contacts": {
        "phone": "+375 17 309-80-00",
        "email": "doubletreeminsk.info@hilton.com"
    },
    "arrivalTime": {
        "checkIn": "14:00",
        "checkOut": "12:00"
    }
}
```

**Пример ответа:** аналогичен `GET /property-view/hotels/{id}`

### 5. Добавление удобств к отелю
**Метод:** `POST /property-view/hotels/{id}/amenities`

**Пример запроса:**
```json
[
    "Free parking",
    "Free WiFi",
    "Non-smoking rooms"
]
```

### 6. Получение гистограммы по параметру
**Метод:** `GET /property-view/histogram/{param}`

Параметры: `brand`, `city`, `county`, `amenities`.

**Пример:** `/property-view/histogram/city`

**Пример ответа:**
```json
{
    "Minsk": 1,
    "Moscow": 2,
    "Mogilev": 0
}
```

## Технологии
- **Java 17+**
- **Spring Boot**
- **Spring JPA**
- **Liquibase**
- **Maven**
- **H2 Database** (с возможностью переключения на MySQL, PostgreSQL, MongoDB)

## Дополнительные возможности
- Документация Swagger
- Разделение на слои
- Гибкость в выборе базы данных

## Запуск приложения

1. **Склонировать репозиторий:**
   ```sh
   git clone https://github.com/uluanaparf/innowese-TestTask-Hotels.git
   ```
2. **Перейти в папку проекта:**
   ```sh
   cd innowese-TestTask-Hotels
   ```
3. **Собрать и запустить приложение:**
   ```sh
   mvn spring-boot:run
   ```
4. **Приложение будет доступно на порту 8092**

---
## Важные замечания
- В репозитории `.gitignore` исключает папку `/data/`, поэтому база данных не содержит предзаполненных данных.
- Перед использованием API рекомендуется заполнить базу данных вручную или настроить автоматическое наполнение с помощью Liquibase.
---
## Swagger-документация
После запуска API, Swagger-документация доступна по адресу:
```
http://localhost:8092/swagger-ui/index.html
```


