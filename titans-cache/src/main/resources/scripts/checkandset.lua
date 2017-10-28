local key = KEYS[1]
local limit  = 0
limit  = tonumber(ARGV[1])
local current = 0
current = tonumber(redis.call("GET",key) or "0")
if current == nil then
current = 0
end

if(current + 1 > limit) then
    return 0
else
    redis.call("INCRBY",key,1)
    redis.call("expire",key,60)
    return 1
end