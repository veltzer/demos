#!/bin/sh
echo > execution.out

# single table queries
echo "EXPLAIN SELECT * FROM film" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film" >> execution.out
echo "EXPLAIN SELECT * FROM film WHERE length>140" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film WHERE length>140" >> execution.out
echo "EXPLAIN SELECT * FROM film WHERE film_id=100" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film WHERE film_id=100" >> execution.out
echo "EXPLAIN SELECT * FROM film WHERE title LIKE 'North%'" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film WHERE title LIKE 'North%'" >> execution.out
echo "EXPLAIN SELECT * FROM film WHERE title LIKE 'North%' AND language_id=1" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film WHERE title LIKE 'North%' AND language_id=1" >> execution.out
echo "EXPLAIN SELECT * FROM film ORDER BY title" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film ORDER BY title" >> execution.out
echo "EXPLAIN SELECT title FROM film ORDER BY title" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT title FROM film ORDER BY title" >> execution.out
echo "EXPLAIN SELECT * FROM film WHERE title LIKE '%North'" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film WHERE title LIKE '%North'" >> execution.out

# more than single table queries
echo "SELECT * FROM film JOIN film_actor USING (film_id) JOIN actor USING (actor_id)" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film JOIN film_actor USING (film_id) JOIN actor USING (actor_id)" >> execution.out
echo "SELECT * FROM film JOIN actor ON (film.title = actor.first_name)" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film JOIN actor ON (film.title = actor.first_name)" >> execution.out
echo "SELECT * FROM film JOIN actor ON (film.description = actor.first_name)" >> execution.out
mysql sakila -t -e "EXPLAIN SELECT * FROM film JOIN actor ON (film.description = actor.first_name)" >> execution.out

# using maatkit
echo "EXPLAIN SELECT * FROM film JOIN film_actor USING (film_id) JOIN actor USING (actor_id)" >> execution.out
mysql sakila -e "EXPLAIN SELECT * FROM film JOIN film_actor USING (film_id) JOIN actor USING (actor_id)" | mk-visual-explain >> execution.out
