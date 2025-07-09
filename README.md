# ☕ CafeOrderSystem

**CafeOrderSystem** — учебное Java-приложение для управления кафе. Реализует регистрацию клиентов, создание заказов и работу с меню.  
Поддерживает две версии:
- ✅ **v1.0.0** — консольная версия
- 🌐 **v2.0.0** — клиент-серверная архитектура с графическим интерфейсом (Swing + сокеты)

---

## 📦 Сборки и версии

| Версия     | Описание                                                       | Скачать |
|------------|----------------------------------------------------------------|---------|
| ✅ **v1.0.0** | Консольное приложение с полной логикой управления кафе        | [⬇️ JAR](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v1.0.0/CafeOrderSystem-fat.jar.zip) |
| 🌐 **v2.0.0** | Клиент-серверная архитектура, GUI на Swing, передача через сокеты | 🔽 [Сервер](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderServer-GUI-fat.jar)  
🖥️ [Клиент](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderClient-GUI-fat.jar) |

---

## ✅ Как запустить v1.0.0 (консольная версия)

1. Установите [JDK 17+](https://adoptium.net/temurin/releases/)
2. Скачайте `CafeOrderSystem-fat.jar.zip`, распакуйте
3. Откройте терминал и выполните:

```bash
java -jar CafeOrderSystem-fat.jar
```

---

## 🌐 Как запустить v2.0.0 (клиент-сервер + GUI)

1. Установите [JDK 17+](https://adoptium.net/temurin/releases/)
2. Скачайте и запустите **сервер**:

```bash
java -jar CafeOrderServer-GUI-fat.jar
```

3. Затем запустите **клиент**:

```bash
java -jar CafeOrderClient-GUI-fat.jar
```

4. Введите IP-адрес и порт сервера (по умолчанию порт: `12345`)

---

## 🔐 Доступ администратора

Для входа под админом используйте:

- **Email:** `sergey@gmail.com`  
- **Пароль:** `321`

Можно также зарегистрировать нового пользователя и войти как обычный клиент.

---

## 📑 Возможности

- 👤 Регистрация и авторизация (роли: пользователь / админ)
- 🍽️ Добавление и удаление блюд из меню
- 🧾 Создание, просмотр и удаление заказов
- 📦 Сохранение и загрузка данных (формат JSON)
- 🧪 Модульные тесты (JUnit 5)
- 🌐 Клиент-сервер через сокеты (v2.0.0)
- 🎨 Графический интерфейс Swing (v2.0.0)

---

## 🛠️ Технологии

- Java 17+
- Swing
- Socket API (TCP)
- Jackson (JSON)
- JUnit 5

---

## 📇 Автор

**Сергей Черевиченко**  
📧 Email: cherevichenkoSN@gmail.com  
🔗 GitHub: [github.com/SergeyCherevichenko](https://github.com/SergeyCherevichenko)
