#language: ru
  Функция: Создание Баг-репорта и перевод по статусам до закрытого
    Сценарий: Создание Баг-репорта с полным заполнением на статусе заведения, перевод задачи по статусам до закрытого
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
      Когда пользователь создает Баг-репорт с полным заполнением на статусе заведения
      Тогда задача создана
      Когда пользователь открывает созданную задачу
      Также статус задачи - "СДЕЛАТЬ"
      Когда пользователь изменяет статус задачи на - "В РАБОТЕ"
      Тогда статус задачи - "В РАБОТЕ"
      Когда пользователь изменяет статус задачи на - "ГОТОВО"
      Тогда статус задачи - "ГОТОВО"