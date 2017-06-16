local key = KEYS[1]
local limit1  = tonumber(ARGV[1])
local current = 0
current = tonumber(redis.call("GET",key) or "0")
if current == nil then
    return -2
end
if limit1 == nil then
    return -10
end
if(current + 1 > limit1) then
    return 0
else
    redis.call("INCRBY",key,1)
    redis.call("expire",key,60)
    return 1
end