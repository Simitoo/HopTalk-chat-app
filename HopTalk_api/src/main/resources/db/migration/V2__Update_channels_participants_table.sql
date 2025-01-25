-- Drop the existing table
DROP TABLE channels_participants;

-- Create the updated table
CREATE TABLE channels_participants (
    id INT IDENTITY(1,1) PRIMARY KEY,
    channel_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(20) CHECK (role IN ('Owner', 'Admin', 'Participant')) DEFAULT 'Participant',
    is_active BIT DEFAULT 1,
    FOREIGN KEY (channel_id) REFERENCES group_channels(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);