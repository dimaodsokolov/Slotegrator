Feature:Login into system

  Scenario: Login to the admin panel
    Given открыть браузер
    And  Авторизоваться в админке
    And  Открыть список игроков
    And Отсортировать по любому столбцу
    Then Список отсортирован
    And Закрыть браузер


