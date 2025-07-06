## Задание 1

[Диаграмма контейнеров](docs/diagrams/containers/CinemaAbyss_to_be.puml)


## Задание 2

Сервисы proxy и events реализованы, код лежит в требуемых директориях, сервисы запускаются и доступны.
Тесты пройдены, результаты:

[Лог прохождения тестов](tests/results/test-log.png)

[Лог сервиса events при получении сообщений](tests/results/service-log.png)

[Состояние топиков Kafka](tests/results/topics.png)

[Состояние топика movie-events](tests/results/topic-movie-events.png)

[Состояние топика payment-events](tests/results/topic-payment-events.png)

[Состояние топика user-events](tests/results/topic-user-events.png)

## Задание 3

Тесты в Github "зеленые", артефакты появились в registry и загрузились при поднятии сервисов в Kubernetes.

[Скриншот вызова https://cinemaabyss.example.com/api/movies](tests/results/kubernetes-get-movies.png)
[Скриншот лога events-service после нескольких прогонов тестов](tests/results/kubernetes-events-log.png)

## Задание 4

Конфигурации Helm обновлены, сервисы запускаются при поднятии Helm, запросы ходят корректно.

[Скриншот развертывания Helm](tests/results/helm-deploy-log.png)
[Скриншот вывода списка фильмов после развертывания Helm](tests/results/helm-get-movies.png)


# Задание 5

Istio подключился, circuit breaker настроен в файле src/kubernetes/circuit-breaker-config.yaml.

[Скриншот теста circuit breaker](tests/results/isio-result.png)