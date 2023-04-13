# social-media-api
#ALL End points 
AUTHENTICATION request mapping (authentication)
[post] registration => registration
[get] update/password => to get code for update password
[post] update/password => to send code for update password
[post] authenticate

CHANNEL request mapping (channel)
[post] new => to create new channel
[delete] {id} => to delete by id
[get] all => get all channels -> only for admin
[get] {id} => get channel by id

USER request mapping (user)
[get] all => get all users
[delete] {id} => to delete by id
[put] {id} => update user
[post] friend/action => follow || unfollow || block || unblock other user
[get] friend/all/{id} => get user friends by id
[get] black/all/{id} => get user black list by id
[get] status/{firstUserId}/{secondUserId} => get status(between two users NEUTRAL || FRIEND || BLOCKED)

IMAGE request mapping(image)
[post]  {address}/{id}  =>  save and attach image
[get] {id} => get by id
[delete] {id} => delete by id

CHAT request mapping (chat)
[get] {id} => get chat by id
[post] new/{firstUser}/{secondUser}/?type=(default PRIVATE) => start new chat
[delete] {id} =>  delete chat by id
[put] leave?chatId=?&userId=? => to leave chat by chat id and user id
[put] join?chatId=?&userId=? => to join chat by chat id and user id
[get] all => get all chats only for admins

COMMENT request mapping (comment)
[post] new => new comment
[get] {id} =>  get comment by id
[delete] {id} => delete chat by id

MESSAGE request mapping (message)
[post] new =>send new message
[delete] {id} => delete message by id
[put] update message

POST request mapping (post)
[post] new => publish new post
[delete] {id} => delete post by id
[get] all/{id} => get user posts by id
[get] all => get all posts only for admin

NOTIFICATION request mapping (notification)
[put]  set/checked/{id} => set checked status to notification by id
[delete] {id} => delete chat
[delete] all/{id} => delete all user notifications by id




