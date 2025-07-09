# ☕ CafeOrderSystem

**CafeOrderSystem** — это учебное Java-приложение для управления кафе. Поддерживает работу с клиентами, заказами и меню в двух версиях:  
🔹 **v1.0.0** — консольная версия  
🔹 **v2.0.0** — клиент-серверная архитектура с GUI (Swing + сокеты)

---

## 📦 Сборки и версии

| Версия | Описание | Скачать |
|--------|----------|---------|
| ✅ **v1.0.0** | Консольное приложение с полной логикой | [⬇️ Скачать JAR](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v1.0.0/CafeOrderSystem-fat.jar.zip) |
| 🌐 **v2.0.0** | Клиент-серверная архитектура с графическим интерфейсом | 🔽 [Сервер](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderServer-GUI-fat.jar)<br>🖥️ [Клиент](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderClient-GUI-fat.jar) |

---

## 🚀 Как запустить

### ☑️ Консольная версия (v1.0.0)

1. Установите [JDK 17+](https://adoptium.net/temurin/releases/)
2. Скачайте `CafeOrderSystem-fat.jar.zip`, распакуйте
3. В терминале выполните:

```bash
java -jar CafeOrderSystem-fat.jar
### 🌐 Клиент-серверная версия (v2.0.0)
Установите JDK 17+

Скачайте и запустите сервер:
java -jar CafeOrderServer-GUI-fat.jar
Затем запустите клиент (на той же или другой машине):
java -jar CafeOrderClient-GUI-fat.jar
🔐 Доступ администратора
Для входа под админом используйте:

Email: sergey@gmail.com

Пароль: 321

Вы также можете зарегистрировать нового пользователя и использовать обычный режим.

📑 Возможности
👤 Регистрация и авторизация (роли: пользователь / администратор)

🧾 Создание и управление заказами

🍽️ Добавление и удаление блюд в меню

🗂️ Сохранение и загрузка данных в формате JSON

🧪 Покрытие модульными тестами (JUnit 5)

🌐 Обмен данными через сокеты и DTO (в v2.0.0)

🎨 Графический интерфейс на Swing (в v2.0.0)

🛠️ Технологии
Java 17+

Swing (GUI)

Socket API (TCP)

Jackson (для JSON сериализации)

JUnit 5 (тестирование)

📇 Контакты
Автор: Сергей Черевиченко
📧 Email: cherevichenkoSN@gmail.com
🔗 GitHub: github.com/SergeyCherevichenko

yaml
Копировать
Редактировать
