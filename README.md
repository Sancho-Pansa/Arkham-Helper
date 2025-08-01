**Arkham Helper** – это приложение для проведения партий в настольную игру "Ужас Аркхэма" (второе издание).
Это приложение призвано помочь ведущему и игрокам отслеживать состояние партии и своих персонажей, что является довольно сложной задачей в условиях множества одновременно действующих переменных. Не последним также является тот факт, что настольная игра предполагает расслабленную атмосферу с перерывами, что не способствует концентрации внимания и отслеживанию всех игровых механик.

# Архитектура
Мобильное приложение на Java + Kotlin.
Данные об объектах игры хранятся в JSON файлах, хранящихся в директории программы.
# Общее описание
Предлагается два режима игры: режим ведущего и режим игрока.
## Режим ведущего
Режим по умолчанию. Используется как помощник ведущему для отслеживания различных счетчиков игры, глобальных событий и бонусов из разных источников, а также как источник подсказок и правил по игре.
Приложение переключает фазы игры, ведет подсчет треков безысходности и ужаса, число монстров на карте и окраинах.

## Режим игрока
Режим игрока в идеале позволяет полностью отказаться от карты игрока и необходимости вести подсчеты жетонов, карт предметов и прочих мелочей. Удобно, если компания ограничена в пространстве и не может на полную разложить все составляющие игры.