#language: ru
  Функция: Поиск задачи по названию, проверка полей
    Сценарий: Поиск задачи по названию, проверка полей "Статус" и "Исправить в версиях"
      Дано  пользователь заходит на главную страницу
      Когда пользователь вводит "Имя пользователя" и "Пароль" и нажимает кнопку "Войти"
      Тогда пользователь авторизован
      Когда пользователь переходит в проект "Test"
      Тогда пользователь находится на странице проекта "Test"
      Когда пользователь создает одну новую задачу
      Тогда счетчик открытых задач увеличивается на 1
      Когда пользователь находит и открывает задачу "TestSeleniumATHomework"
      Тогда статус задачи - "СДЕЛАТЬ"
      Также исправить в версиях - "Version 2.0"