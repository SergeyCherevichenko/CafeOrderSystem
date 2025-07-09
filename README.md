# ☕ CafeOrderSystem

**CafeOrderSystem** — это учебное Java-приложение для управления кафе. Поддерживает работу с клиентами, заказами и меню в двух версиях: **консольная (v1.0.0)** и **клиент-серверная с GUI (v2.0.0)**.

---

## 📦 Сборки и версии

| Версия | Описание | Скачать |
|--------|----------|---------|
| ✅ **v1.0.0** | Консольное приложение с полной логикой | [⬇️ Скачать JAR](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v1.0.0/CafeOrderSystem-fat.jar) |
| 🌐 **v2.0.0** | Клиент-серверная архитектура с GUI (Swing + сокеты) | 🔽 [Сервер](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderServer-GUI-fat.jar)<br>🖥️ [Клиент](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderClient-GUI-fat.jar) |

---

## 🚀 Как запустить

### ☑️ Консольная версия (v1.0.0)

1. Установите [JDK 17+](https://adoptium.net/temurin/releases/)
2. Скачайте `CafeOrderSystem-fat.jar`
3. Запустите:

```bash
java -jar CafeOrderSystem-fat.jar
🌐 Клиент-серверная версия (v2.0.0)
Установите JDK 17+

Запустите сначала сервер:

bash
Копировать
Редактировать
java -jar CafeOrderServer-GUI-fat.jar
Затем на другой (или той же) машине — клиент:

bash
Копировать
Редактировать
java -jar CafeOrderClient-GUI-fat.jar
Введите IP и порт сервера (например, 192.168.1.10 и 12345)

🔐 Доступ администратора
Чтобы войти под админом:

Email: sergey@gmail.com

Пароль: 321

Вы также можете зарегистрировать нового пользователя.

📑 Возможности
👤 Регистрация и авторизация (пользователь / админ)

🧾 Создание и управление заказами

🍔 Добавление и удаление блюд из меню

🗂️ Сохранение и загрузка данных (JSON)

🧪 Модульные тесты (JUnit 5)

🌐 Сокет-сервер (v2.0.0) и обмен в формате JSON

🎨 Swing GUI интерфейс (в v2.0.0)

📇 Контакты
Автор: Сергей Черевиченко

Email: cherevichenkoSN@gmail.com

GitHub: SergeyCherevichenko

🛠️ Технологии
Java 17+

Swing

Socket API

Jackson (JSON)

JUnit 5

yaml
Копировать
Редактировать
