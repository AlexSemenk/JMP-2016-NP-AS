SELECT DISTINCT name FROM users
JOIN (
	SELECT userid1 AS 'userid', count(*) AS 'friends_number'
	FROM friendships
	WHERE timestamp < '2015-04-01'
	GROUP BY userid1
) f
ON users.id = f.friends_number
JOIN (
    SELECT posts.userid as 'userid', SUM(l.likes_number) as 'likes_number'
    FROM posts 
    JOIN (
        SELECT postid as 'postid', COUNT(*) as 'likes_number'
        FROM likes
        WHERE timestamp < '2015-04-01'
        GROUP BY postid
    ) l
    ON posts.id = l.postid
    GROUP BY posts.userid
) p
ON users.id = p.userid
WHERE friends_number > 100
  AND likes_number > 100