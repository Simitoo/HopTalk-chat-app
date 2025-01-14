-- Users table
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    icon_url VARCHAR(2083),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1
);

-- Users-Friends table
CREATE TABLE users_friends (
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1,
    PRIMARY KEY(user_id, friend_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(friend_id) REFERENCES users(id)
);

-- Direct Channels table
CREATE TABLE direct_channels (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    participant_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1,
    UNIQUE (user_id, participant_id),
    UNIQUE (participant_id, user_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(participant_id) REFERENCES users(id)
);

-- Group Channels table
CREATE TABLE group_channels (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    icon_url VARCHAR(2083),
    creator_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1,
    FOREIGN KEY (creator_id) REFERENCES users(id)
);

-- Channel's participants table
CREATE TABLE channels_participants (
    channel_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(20) CHECK (role IN ('Owner', 'Admin', 'Participant')) DEFAULT 'Participant',
    is_active BIT DEFAULT 1,
    PRIMARY KEY(channel_id, user_id),
    FOREIGN KEY (channel_id) REFERENCES group_channels(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Messages table
CREATE TABLE messages (
    id INT IDENTITY(1,1) PRIMARY KEY,
    receiver_id INT NOT NULL,
    receiver_type VARCHAR(20) CHECK (receiver_type IN ('user', 'channel')) NOT NULL,
    sender_id INT NOT NULL,
    message VARCHAR(1000),
    attachment_id INT,
    attachment_url VARCHAR(2083),
    message_type VARCHAR(20) CHECK (message_type IN ('text', 'sticker', 'gif', 'file')) DEFAULT 'text',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1,
    FOREIGN KEY (sender_id) REFERENCES users(id)
);

-- Stickers table
CREATE TABLE stickers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(2083) NOT NULL,
    collection_name VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BIT DEFAULT 1
);